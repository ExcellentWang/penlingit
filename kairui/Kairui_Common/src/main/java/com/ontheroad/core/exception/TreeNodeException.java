package com.ontheroad.core.exception;

public class TreeNodeException extends BaseException {
	private static final long serialVersionUID = 3787730660315875183L;

	public TreeNodeException(String message) {
		super(message);
	}

	@Override
	protected String getTitle() {
		return message;
	}

}
