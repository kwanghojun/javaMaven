package com.batchboot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class lottoDto {
	String lotto_turn;
	Integer lotto_num1;
	Integer lotto_num2;
	Integer lotto_num3;
	Integer lotto_num4;
	Integer lotto_num5;
	Integer lotto_num6;
	Integer lotto_plus;
}
