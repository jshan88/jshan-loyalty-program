package com.loyalty.jshan.member.service.address;

import com.loyalty.jshan.member.domain.Member;
import com.loyalty.jshan.member.domain.address.Address;
import com.loyalty.jshan.member.domain.address.AddressRepository;
import com.loyalty.jshan.member.dto.address.AddressEnrollmentDto;
import com.loyalty.jshan.member.dto.address.AddressUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public void enrollAddress(Member member, List<AddressEnrollmentDto> requestDtos) {

        for (AddressEnrollmentDto addressEnrollmentDto : requestDtos) {
            Address address = addressEnrollmentDto.toEntity();
            addressRepository.save(address);
            member.mapAddressToMember(address);
        }
    }

    public void updateAddressList(Member member, List<AddressUpdateDto> requestDtos) {

        // Update Addresses with the same Address Types that are given.
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
    public void updateAddress(Address address, AddressUpdateDto requestDto) {
        address.updateAddress(requestDto.getAddressType(), requestDto.getCountry(), requestDto.getZipCode(),
                        requestDto.getAddress1(), requestDto.getAddress2());
    }
}
 