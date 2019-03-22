package com.foxhis.email;

import org.apache.http.annotation.Immutable;
import org.apache.http.conn.ssl.AbstractVerifier;

@Immutable
public class NoopHostnameVerifier
extends AbstractVerifier
{
	public static final NoopHostnameVerifier INSTANCE = new NoopHostnameVerifier();

	

	public final String toString()
	{
		return "NO_OP";
	}



	@Override
	public void verify(String host, String[] cns, String[] subjectAlts) {
		// TODO Auto-generated method stub
		
	}

	
}
