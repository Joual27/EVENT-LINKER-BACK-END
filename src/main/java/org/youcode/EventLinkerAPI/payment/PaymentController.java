package org.youcode.EventLinkerAPI.payment;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.EventLinkerAPI.payment.DTOs.CreatePaymentIntentDTO;
import org.youcode.EventLinkerAPI.payment.DTOs.PaymentResponseDTO;
import org.youcode.EventLinkerAPI.payment.interfaces.PaymentService;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.SuccessDTO;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/organizer/payments")
public class PaymentController {
      private final PaymentService paymentService;

      @PostMapping("/create-intent")
      public ResponseEntity<SuccessDTO<PaymentResponseDTO>> createPaymentIntent(@RequestBody @Valid CreatePaymentIntentDTO req){
          PaymentResponseDTO res = paymentService.createPaymentIntent(req);
          return new ResponseEntity<>(new SuccessDTO<>("success " , "Payment Intent Created Successfully !" , res ) , HttpStatus.CREATED);
      }
}
