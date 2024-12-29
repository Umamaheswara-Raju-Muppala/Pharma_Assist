package com.pharma_assist.service;

import org.springframework.stereotype.Service;

import com.pharma_assist.entity.Cart;
import com.pharma_assist.entity.Item;
import com.pharma_assist.entity.Medicine;
import com.pharma_assist.exceptions.CartNotFoundException;
import com.pharma_assist.exceptions.InsufficientQuantityException;
import com.pharma_assist.exceptions.MedicineNotFoundException;
import com.pharma_assist.repository.CartRepository;
import com.pharma_assist.repository.ItemRepository;
import com.pharma_assist.repository.MedicineRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {

	private final CartRepository cartRepository;
	private final MedicineRepository medicineRepository;
	private final ItemRepository itemRepository;

	public CartService(CartRepository cartRepository, MedicineRepository medicineRepository,
			ItemRepository itemRepository) {
		this.cartRepository = cartRepository;
		this.medicineRepository = medicineRepository;
		this.itemRepository = itemRepository;
	}

	public String createCart() {
		Cart cart = new Cart();
		cartRepository.save(cart);
		return "CartID : " + cart.getCartId();
	}

	@Transactional
	public String addItemIntoCart(String cartId, String medicineId, int quantity) {

		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new CartNotFoundException("Cart Not Found or Invalid Cart ID " + cartId));
		Medicine medicine = medicineRepository.findById(medicineId).orElseThrow(
				() -> new MedicineNotFoundException("Medicine Not Found or Invalid Medicine Id " + medicineId));

		if (medicine.getStockQuantity() < quantity) {
			throw new InsufficientQuantityException("Insufficieant Stock. Available " + medicine.getStockQuantity()
					+ " Please choose less then or equal quantity that exits in stock ");
		}
		Item item = cart.getItems().stream().filter(items -> items.getMedicine().getMedicineId().equals(medicineId))
				.findFirst().orElse(null);
		if (item != null) {
			item.setQuantity(item.getQuantity() + quantity);
			item.setTotalPrice(item.getItemPrice() * item.getQuantity());
		} else {
			item = new Item();

			item.setName(medicine.getName());
			item.setDosage(medicine.getDosageInMg() + " mg");
			item.setQuantity(quantity);
			item.setItemPrice(medicine.getPrice());
			item.setTotalPrice(quantity * medicine.getPrice());
			item.setMedicine(medicine);
		}
		cart.addItem(item);
		cartRepository.save(cart);
		medicine.setStockQuantity(medicine.getStockQuantity() - quantity);
		medicineRepository.save(medicine);
		return quantity + " " + medicine.getName() + " "
				+ (quantity > 1 ? medicine.getForm() + "s" : medicine.getForm()) + " added into Cart successfully";
	}

	@Transactional
	public String removeItemFromCart(String cartId, String medicineId) {
		System.out.println(cartId);
		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new CartNotFoundException("Cart Not Found or Invalid Cart ID " + cartId));
		Medicine medicine = medicineRepository.findById(medicineId).orElseThrow(
				() -> new MedicineNotFoundException("Medicine Not Found or Invalid Medicine Id " + medicineId));

		Item item = cart.getItems().stream()
				.filter(items -> items.getMedicine().getMedicineId().equals(medicine.getMedicineId())).findFirst()
				.orElse(null);
		cart.removeItem(item);
		medicine.setStockQuantity(medicine.getStockQuantity() + item.getQuantity());
		int quantity = item.getQuantity();
		cartRepository.save(cart);
		itemRepository.deleteById(item.getItemId());
		medicineRepository.save(medicine);

		return medicine.getName() + " " + (quantity > 1 ? medicine.getForm() + "s" : medicine.getForm())
				+ " of quantity " + quantity + " removed from Cart successfully";
	}

}
