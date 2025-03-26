package org.youcode.EventLinkerAPI.payment.interfaces;



import org.youcode.EventLinkerAPI.payment.DTOs.CreatePaymentIntentDTO;
import org.youcode.EventLinkerAPI.payment.DTOs.PaymentResponseDTO;

public interface PaymentService {
    PaymentResponseDTO createPaymentIntent(CreatePaymentIntentDTO data);
}
