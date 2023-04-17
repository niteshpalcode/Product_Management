package com.ecom.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.exceptions.BillNotFoundException;
import com.ecom.exceptions.CartException;
import com.ecom.exceptions.LoginException;
import com.ecom.exceptions.OrderNotFoundException;
import com.ecom.exceptions.UsersNotFoundException;
import com.ecom.model.ProductBill;
import com.ecom.service.ProductBillService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/ecom")
public class ProductBillController {

	@Autowired
	private ProductBillService productBillService;
	
	@PostMapping("/{orderId}/{userId}")
	public ResponseEntity<ProductBill> payBillOfProductHandler(@PathVariable("orderId") Long orderId, @PathVariable("userId") Long userId, @RequestBody ProductBill bill)
			throws OrderNotFoundException,CartException,LoginException,UsersNotFoundException{
		
		return new ResponseEntity<ProductBill>
		(productBillService.totalBillOfUser(bill, orderId, userId),HttpStatus.CREATED);
	}
	
	@GetMapping("/{billId}/{userId}")
	public ResponseEntity<ProductBill> getBillOfProductHandler(@PathVariable("billId") Long billId, @PathVariable("userId") Long userId)
			throws BillNotFoundException,LoginException,UsersNotFoundException{
		
		return new ResponseEntity<ProductBill>
		(productBillService.viewBillById(billId, userId),HttpStatus.OK);
	}
	
}
