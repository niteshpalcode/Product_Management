package com.ecom.service;


import com.ecom.exceptions.BillNotFoundException;
import com.ecom.exceptions.CartException;
import com.ecom.exceptions.LoginException;
import com.ecom.exceptions.OrderNotFoundException;
import com.ecom.exceptions.UsersNotFoundException;
import com.ecom.model.ProductBill;

public interface ProductBillService {

	
	public ProductBill totalBillOfUser(ProductBill bill, Long orderId ,Long userId)
			throws OrderNotFoundException,CartException,LoginException,UsersNotFoundException;
	
	public ProductBill viewBillById(Long billId,Long userId)
			throws BillNotFoundException,LoginException,UsersNotFoundException;;
	
}
