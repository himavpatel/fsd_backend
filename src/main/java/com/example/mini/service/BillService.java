package com.example.mini.service;

import com.example.mini.entity.Bill;
import com.example.mini.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public Optional<Bill> getBillById(Long id) {
        return billRepository.findById(id);
    }

    public Bill createBill(Bill bill) {
        return billRepository.save(bill);
    }

    public Bill updateBill(Long id, Bill billDetails) {
        Bill bill = billRepository.findById(id).orElse(null);
        if (bill == null) {
            return null;
        }
        bill.setPatientId(billDetails.getPatientId());
        bill.setAmount(billDetails.getAmount());
        // Update other bill attributes here
        return billRepository.save(bill);
    }

    public boolean deleteBill(Long id) {
        if (billRepository.existsById(id)) {
            billRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
