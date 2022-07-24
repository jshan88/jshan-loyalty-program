package com.loyalty.jshan.redemption.service;

import com.loyalty.jshan.global.apiException.ApiErrorCode;
import com.loyalty.jshan.global.apiException.ApiRequestException;
import com.loyalty.jshan.member.domain.Member;
import com.loyalty.jshan.member.repository.MemberRepository;
import com.loyalty.jshan.member.service.MemberService;
import com.loyalty.jshan.redemption.domain.Cart;
import com.loyalty.jshan.redemption.domain.Order;
import com.loyalty.jshan.redemption.domain.enums.OrderStatus;
import com.loyalty.jshan.redemption.dto.OrderRequestDto;
import com.loyalty.jshan.redemption.dto.OrderResponseDto;
import com.loyalty.jshan.redemption.repository.CartRepository;
import com.loyalty.jshan.redemption.repository.OrderRepository;
import com.loyalty.jshan.transaction.domain.Transaction;
import com.loyalty.jshan.transaction.domain.enums.SourceType;
import com.loyalty.jshan.transaction.domain.enums.TransactionStatus;
import com.loyalty.jshan.transaction.domain.enums.TransactionSubType;
import com.loyalty.jshan.transaction.domain.enums.TransactionType;
import com.loyalty.jshan.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.loyalty.jshan.global.CommonUtil.generateAuthNumber;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final TransactionRepository transactionRepository;
    private final MemberService memberService;

    @Transactional
    public OrderResponseDto issueOrder(OrderRequestDto requestDto) {

        Cart cart = cartRepository.findById(requestDto.getCartId())
                .orElseThrow(()-> new ApiRequestException(ApiErrorCode.CART_NOT_FOUND));

        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(()-> new ApiRequestException(ApiErrorCode.MEMBER_NOT_FOUND));

        if(member.getRemainMileage() < cart.getTotalMileage()) {
            throw new ApiRequestException(ApiErrorCode.NOT_ENOUGH_MILEAGE);
        }

        Order order = Order.builder()
                        .authNumber(generateAuthNumber(SourceType.FLIGHT))
                        .orderStatus(OrderStatus.ISSUED)
                        .activeFlg("Y")
                        .cart(cart)
                        .member(member)
                        .redeemedMiles(cart.getTotalMileage())
                        .refundMiles(0)
                        .build();

        Transaction txn = Transaction.builder()
                .member(member)
                .order(order)
                .txnType(TransactionType.REDEMPTION)
                .txnSubType(TransactionSubType.PRODUCT)
                .status(TransactionStatus.PROCESSED)
                .sourceType(SourceType.FLIGHT)
                .mileage(cart.getTotalMileage())
                .build();


        member.updateMember(cart.getTotalMileage()*-1);
        orderRepository.save(order);
        transactionRepository.save(txn);

        return toOrderResponse(order);
    }

    @Transactional
    public OrderResponseDto cancelOrder(String authNumber) {

        Order order = orderRepository.findOrderByAuthNumber(authNumber);

        if(order.getOrderStatus().equals(OrderStatus.REFUND)) {
            throw new ApiRequestException(ApiErrorCode.ORDER_ALREADY_CANCELLED);
        }

        Transaction txn = transactionRepository.findTransactionByOrderId(order.getId());

        Transaction cancellationTxn = txn.cancelTransaction();
        Order refundOrder = order.cancelOrder();

        orderRepository.save(refundOrder);
        transactionRepository.save(cancellationTxn);

        return toOrderResponse(refundOrder);
    }

    public OrderResponseDto toOrderResponse (Order order){

        return OrderResponseDto.builder()
                .id(order.getId())
                .memberId(order.getMember().getId())
                .authNumber(order.getAuthNumber())
                .orderStatus(order.getOrderStatus())
                .activeFlg(order.getActiveFlg())
                .redeemedMiles(order.getRedeemedMiles())
                .refundMiles(order.getRefundMiles())
                .build();
    }












}

