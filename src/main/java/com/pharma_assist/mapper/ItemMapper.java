package com.pharma_assist.mapper;

import org.springframework.stereotype.Component;

import com.pharma_assist.entity.Item;
import com.pharma_assist.responses.ItemResponse;

@Component
public class ItemMapper {

	public ItemResponse itemToItemResponse(Item item) {
		return new ItemResponse(item.getItemId(), item.getName(), item.getDosage(),
				item.getQuantity(),item.getItemPrice(), item.getTotalPrice());
	}
}
