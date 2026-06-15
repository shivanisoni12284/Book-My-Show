package com.example.bookmyshow.refund.controller;

import com.example.bookmyshow.booking.schema.Booking;
import com.example.bookmyshow.payment.schema.Payment;
import com.example.bookmyshow.refund.schema.Refund;
import com.example.bookmyshow.refund.schema.RefundStatus;
import com.example.bookmyshow.refund.service.RefundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/refund")
@RequiredArgsConstructor
public class RefundController {

    private final RefundService refundService;
    public ResponseEntity<Refund> getRefundById(Long refundId){
        Refund refund = refundService.getRefundById(refundId);
        return ResponseEntity.ok(refund);

    }
}
