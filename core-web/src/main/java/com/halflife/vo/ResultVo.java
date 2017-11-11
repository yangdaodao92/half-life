package com.halflife.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
public class ResultVo {

	private Integer code = 200;
	@NonNull private Object data;

	public ResultVo() {
	}

	public ResultVo(Integer code, Object data) {
		this.code = code;
		this.data = data;
	}

}
