package com.yash.model;

public class Material {

	public Material(Materials material, int ConsumptionOfMaterial, int wasteOfMaterial) {
		this.materialName = material;
		this.ConsumptionOfMaterial = ConsumptionOfMaterial;
		this.wasteOfMaterial = wasteOfMaterial;
	}
	
	String MaterialId;
	Materials materialName;
	int ConsumptionOfMaterial;
	int wasteOfMaterial;
	
	public Materials getMaterialName() {
		return materialName;
	}
	public int getConsumptionOfMaterial() {
		return ConsumptionOfMaterial;
	}
	public int getWasteOfMaterial() {
		return wasteOfMaterial;
	}
	
 @Override
public String toString() {
	// TODO Auto-generated method stub
	return materialName.toString()+" "+ConsumptionOfMaterial+" "+wasteOfMaterial;
}
	
}
