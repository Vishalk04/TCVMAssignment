package com.yash.model;

public class Material {

	public Material(MaterialTypes material, int ConsumptionOfMaterial, int wasteOfMaterial) {
		this.materialName = material;
		this.ConsumptionOfMaterial = ConsumptionOfMaterial;
		this.wasteOfMaterial = wasteOfMaterial;
	}
	
	String MaterialId;
	MaterialTypes materialName;
	int ConsumptionOfMaterial;
	int wasteOfMaterial;
	
	public MaterialTypes getMaterialName() {
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
