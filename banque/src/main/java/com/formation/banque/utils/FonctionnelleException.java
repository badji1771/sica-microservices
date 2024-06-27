package com.formation.banque.utils;

/**
 * The MyBusinessException wraps all checked standard Java exception and
 * enriches them with a custom error code. You can use this code to retrieve
 * localized error messages and to link to our online documentation
 */
public class FonctionnelleException extends Exception {

	private static final long serialVersionUID = 1L;

	public FonctionnelleException(String message, Throwable cause) {
		super(message, cause);
	}

	public FonctionnelleException(String message) {
		super(message);
	}

	public FonctionnelleException(Throwable cause) {
		super(cause);
	}
}
