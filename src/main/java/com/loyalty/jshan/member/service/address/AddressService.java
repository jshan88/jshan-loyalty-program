package com.loyalty.jshan.member.service.address;

import com.loyalty.jshan.member.domain.Member;
import com.loyalty.jshan.member.domain.address.Address;
import com.loyalty.jshan.member.domain.address.AddressRepository;
import com.loyalty.jshan.member.dto.address.AddressEnrollmentDto;
import com.loyalty.jshan.member.dto.address.AddressResponseDto;
import com.loyalty.jshan.member.dto.address.AddressUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public void enrollAddress(Member member, List<AddressEnrollmentDto> requestDtoList) {

        requestDtoList.forEach(addressEnrollmentDto -> {
           Address address = addressEnrollmentDto.toEntity();
           addressRepository.save(address);
           member.mapAddressToMember(address);
        });
    }

    public void updateAddressList(Member member, List<AddressUpdateDto> requestDtoList) {

        // Update Addresses with the same Address Types that are given.
        for (int i = 0; i < member.getAddressList().size(); i++) {
            for(int j = 0; j < requestDtoList.size(); j++) {
                if(member.getAddressList().get(i).getAddressType() == requestDtoList.get(j).getAddressType()) {
                    updateAddress(member.getAddressList().get(i), requestDtoList.get(j));
                    requestDtoList.remove(j);
                    break;
                }
            }
        }
        // Insert Addresses that do not exist with the given Address Type.
        if(requestDtoList.size() > 0) {
            requestDtoList.forEach(addressUpdateDto -> {
                Address address = addressUpdateDto.toEntity();
                addressRepository.save(address);
                member.mapAddressToMember(address);
            });
        }
    }
    public void updateAddress(Address address, AddressUpdateDto requestDto) {
        address.updateAddress(requestDto.getAddressType(), requestDto.getCountry(), requestDto.getZipCode(),
                        requestDto.getAddress1(), requestDto.getAddress2());
    }

    public AddressResponseDto toAddressResponse(Address address) { 

        return AddressResponseDto.builder()
                        .addressType(address.getAddressType())
                        .country(address.getCountry())
                        .zipCode(address.getZipCode())
                        .address1(address.getAddress1())
                        .address2(address.getAddress2())
                        .build();
    }

    public List<AddressResponseDto> toAddressResponseList(List<Address> addressList) { 

        List<AddressResponseDto> addressResponseList = new ArrayList<>();

        addressList.forEach(
            addr -> addressResponseList.add(toAddressResponse(addr))
        );

        return addressResponseList;
    }
}
 