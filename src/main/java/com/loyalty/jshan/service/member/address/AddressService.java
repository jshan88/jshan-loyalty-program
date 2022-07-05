package com.loyalty.jshan.service.member.address;

import com.loyalty.jshan.domain.member.Member;
import com.loyalty.jshan.domain.member.address.Address;
import com.loyalty.jshan.domain.member.address.AddressRepository;
import com.loyalty.jshan.web.dto.member.MemberEnrollmentDto;
import com.loyalty.jshan.web.dto.member.address.AddressEnrollmentDto;
import com.loyalty.jshan.web.dto.member.address.AddressUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public void mapMemberToAddress(Member member, List<AddressEnrollmentDto> requestDtos) {

//        List<AddressEnrollmentDto> addressDtos = requestDtos;
        Address address;
        for (AddressEnrollmentDto addressEnrollmentDto : requestDtos) {
            address = addressEnrollmentDto.toEntity();

            addressRepository.save(address);
            member.mapAddressToMember(address);
        }
    }

    public void updateAddressList(Member member, List<AddressUpdateDto> requestDtos) {
 

        if (member.getAddressList().size() == 0) {
            for (AddressUpdateDto addressUpdateDto : requestDtos) {
                Address address = addressUpdateDto.toEntity();
                addressRepository.save(address);
                member.mapAddressToMember(address);
            }
        } else { 

            // Update Addresses that exist with the same Address Type. 
            for (int i = 0; i < member.getAddressList().size(); i++) {
                for(int j = 0; j < requestDtos.size(); j++) {
                    if(member.getAddressList().get(i).getAddressType() == requestDtos.get(j).getAddressType()) {
                        updateAddress(member.getAddressList().get(i), requestDtos.get(j));
                        requestDtos.remove(j);
                        break;
                    }
                }
            }

            // Insert Addresses that do not exist with the given Address Type. 
            if(requestDtos.size() > 0) { 
 
                requestDtos.stream().forEach(addr -> {

                    Address address = addr.toEntity();
                    addressRepository.save(address);
                    member.mapAddressToMember(address);

                });
            }
        }
    }

    public void updateAddress(Address address, AddressUpdateDto requestDto) {
        address.updateAddress(requestDto.getAddressType(), requestDto.getCountry(), requestDto.getZipCode(),
                        requestDto.getAddress1(), requestDto.getAddress2());
    }
}
 