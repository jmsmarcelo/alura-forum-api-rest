package com.github.jmsmarcelo.alura.forum.api.base;

public class InfoData {
	public static DataDetail get(Status status, String msg) {
		Data.status = status;
		Data.msg = msg;
		return new DataDetail();
	}
	private class Data {
		private static Status status;
		private static String msg;
	}
	public record DataDetail(Status status, String message) {
		public DataDetail() {
			this(Data.status, Data.msg);
		}
	}
	public enum Status {
		DELETED
	}
}
