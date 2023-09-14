package com.github.jmsmarcelo.alura.forum.api.base;

public class InfoData {
	public static DataFormat get(Status status, String msg) {
		Data.status = status;
		Data.msg = msg;
		return new DataFormat();
	}
	private class Data {
		private static Status status;
		private static String msg;
	}
	public record DataFormat(Status status, String message) {
		public DataFormat() {
			this(Data.status, Data.msg);
		}
	}
	public enum Status {
		DELETED
	}
}
