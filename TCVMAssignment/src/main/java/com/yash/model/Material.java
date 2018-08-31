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

	
	public String getMaterialId() {
		return MaterialId;
	}
	public void setMaterialId(String materialId) {
		MaterialId = materialId;
	}
	
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
