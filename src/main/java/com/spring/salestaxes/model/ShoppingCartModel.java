package com.spring.salestaxes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="shoppingcart")
public class ShoppingCartModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	private long idProduto;
	
	@NotNull
	private String nomeProduto;
	
	@NotNull
	private double valorUnitario;
		
	@NotNull
	private int qtdProduto;
	
	@NotNull
	private double valorCompra;
	
	@NotNull
	private int imported;
	
	@NotNull
	private String category;
	
	private String cover64;
	
	public String getCover64() {
		return cover64;
	}
		
	public void setCover64(String cover64) {
		this.cover64 = cover64;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdProduto() {
		return idProduto;
	}
	
	public void setIdProduto(long id) {
		this.idProduto = id;
	}
	
	public int getQtdProduto() {
		return qtdProduto;
	}
	
	public void setQtdProduto(int quantidade) {
		this.qtdProduto = quantidade;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}
	
	public int getImported() {
		return imported;
	}

	public void setImported(int imported) {
		this.imported = imported;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
		
}
