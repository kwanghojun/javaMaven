package com.batchboot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class testLowDto {
	
	private int seqColumn;
    private String numseq;
    private String grp_id;
    private String col1;
    private String col2;
    private String col3;
    private String etc;
    private String bigo;
    private String regdt;
    
	public int getSeqColumn() {
		return seqColumn;
	}
	public void setSeqColumn(int seqColumn) {
		this.seqColumn = seqColumn;
	}
	public String getNumseq() {
		return numseq;
	}
	public void setNumseq(String numseq) {
		this.numseq = numseq;
	}
	public String getGrp_id() {
		return grp_id;
	}
	public void setGrp_id(String grp_id) {
		this.grp_id = grp_id;
	}
	public String getCol1() {
		return col1;
	}
	public void setCol1(String col1) {
		this.col1 = col1;
	}
	public String getCol2() {
		return col2;
	}
	public void setCol2(String col2) {
		this.col2 = col2;
	}
	public String getCol3() {
		return col3;
	}
	public void setCol3(String col3) {
		this.col3 = col3;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getBigo() {
		return bigo;
	}
	public void setBigo(String bigo) {
		this.bigo = bigo;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
        
}
