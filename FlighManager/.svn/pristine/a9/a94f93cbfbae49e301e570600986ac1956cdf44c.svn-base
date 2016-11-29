package edu.msg.flightmanager.backend.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.msg.flightmanager.backend.repository.PasswordEncryptingException;

public class PasswordEncrypter {

	private static final Logger LOG = LoggerFactory.getLogger(PasswordEncrypter.class);

	public static String generateHashedPassword(final String password) throws PasswordEncryptingException {
		String hashedPassword = "";
		byte[] initialBytes;
		try {
			initialBytes = (password).getBytes("utf-8");
			final MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			algorithm.reset();
			algorithm.update(initialBytes);
			final byte[] hashedBytes = algorithm.digest();
			hashedPassword = new String(hashedBytes);
		} catch (final UnsupportedEncodingException e) {
			PasswordEncrypter.LOG.error("Password encryption failed: unsupported encoding", e);
			throw new PasswordEncryptingException("Password encryption failed: unsupported encoding");
		} catch (final NoSuchAlgorithmException nsae) {
			PasswordEncrypter.LOG.error("Password encryption failed: hashing algorithm not supported", nsae);
			throw new PasswordEncryptingException("Password encryption failed: hashing algorithm not supported");
		}
		return hashedPassword;
	}

}
