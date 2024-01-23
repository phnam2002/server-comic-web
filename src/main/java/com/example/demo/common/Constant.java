package com.example.demo.common;

public class Constant {
	
	public final static String OK = "OK";
	public final static String ERROR = "ERROR";
	public final static Long LIMIT_LOGIN_FALSE = 3l;// số lần nhập sai mật khẩu liên tiếp
	public final static String QUALITY_PASSWORD = "82";// Số lượng bản ghi password his cần lấy
	public final static String QUALITY_CUST = "71";// Số lượng bản ghi password his cần lấy

	public enum FwError {
		THANHCONG("THANHCONG", "Thành công!"),
		KHONGTHANHCONG("KHONGTHANHCONG", "Không thành công!"),
		DLKHONGTONTAI("DLKHONGTONTAI", "Dữ liệu không tồn tại!"),
		DUPLICATEPASSWORD("DUPLICATEPASSWORD", "Mật khẩu mới không được trùng với mật khẩu cũ!"),
		PASSWORDNOTFOUND("PASSWORDNOTFOUND", "Mật khẩu hiện tại không chính xác!"),
		PASSWORDFAILLIMIT("PASSWORDFAILLIMIT", "Bạn đã nhập sai mật khẩu. Tài khoản sẽ bị thoát"),
		DLDATONTAI("DLDATONTAI", "Dữ liệu đã tồn tại!"),
		SAIDINHDANG("SAIDINHDANG", "Định dạng không hợp lệ"),
		SAIHANMUC("SAIHANMUC", "Hạn mức giao dịch không hợp lệ"),
		SAITRANGTHAI("SAITRANGTHAI", "Sai trạng thái cập nhật"),
		QUASOLUONG("QUASOLUONG", "Quá số lượng cho phép"),
		SOTIENLONHONHANMUCLAPLENH("SOTIENLONHONHANMUCLAPLENH",
				"Số tiền lớn hơn hạn mức của mã lập lệnh"),
		SOTIENLONHONHANMUCDUYETLENH("SOTIENLONHONHANMUCDUYETLENH",
				"Số tiền lớn hơn hạn mức của mã duyệt lệnh"),
		SOTIENLONHONHANMUCDOANHNGHIEP("SOTIENLONHONHANMUCDOANHNGHIEP", "Số tiền lớn hơn hạn mức doanh nghiệp"),
		SOTIENLONHONHANMUCTOIDA("SOTIENLONHONHANMUCTOIDA", "Số tiền lớn hơn hạn mức tối đa"),
		SOTIENNHOHONHANMUCTOITHIEU("SOTIENNHOHONHANMUCTOITHIEU", "Số tiền nhỏ hơn hạn mức tối thiểu"),
		SOGIAODICHLONHONHANMUC("SOGIAODICHLONHONHANMUC", "Số giao dịch lớn hơn hạn mức số lần giao dịch");

		private final String code;
		private final String message;

		FwError(String code, String message) {
			this.code = code;
			this.message = message;
		}

		public String getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}

	}

	public final static class Prequency {
		public final static String NGAY = "1";
		public final static String TUAN = "2";
		public final static String THANG = "3";
		public final static String QUY = "4";
		public final static String NAM = "5";
	}

}
