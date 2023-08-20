package com.alejandro.animeninja.bussines.services;

import java.io.IOException;

public interface CompressedCacheService {
	
	void putToCache(String cacheName, String key, Object value) ;
	Object getFromCache(String cacheName, String key) ;

}
