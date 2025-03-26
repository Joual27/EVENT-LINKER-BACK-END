package org.youcode.EventLinkerAPI.payment.mapper;

import org.mapstruct.Mapper;
import org.youcode.EventLinkerAPI.payment.DTOs.EmbeddedPaymentDTO;
import org.youcode.EventLinkerAPI.payment.Payment;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.mappers.BaseEmbeddedMapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper extends BaseEmbeddedMapper<Payment , EmbeddedPaymentDTO> {
}
