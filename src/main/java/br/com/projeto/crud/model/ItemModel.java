package br.com.projeto.crud.model;

import br.com.projeto.crud.model.dto.ItemCreateDTO;
import br.com.projeto.crud.model.dto.ItemUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public @Getter @Setter class ItemModel {
	private int id;
	private String name;

	public ItemModel(ItemCreateDTO dto) {
		name = dto.getName();
	}

	public ItemModel(int id, ItemUpdateDTO dto) {
		name = dto.getName();
		this.id = id;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", name:" + name + "}";
	}

}