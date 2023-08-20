package com.alejandro.animeninja.bussines.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;


import com.alejandro.animeninja.bussines.services.CompressedCacheService;
import com.github.luben.zstd.Zstd;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

// Si estás usando Apache Commons IO para el método IOUtils.copy():
import org.apache.commons.io.IOUtils;
import org.openjdk.jol.info.GraphLayout;



@Service
public class CompressedCacheServiceImpl implements CompressedCacheService{

	    @Autowired
	    private CacheManager cacheManager;

	    public void putToCache(String cacheName, String key, Object value) {
	        Cache cache = cacheManager.getCache(cacheName);
	        if (cache != null && value != null) {
	            byte[] compressedValue = compressZstandard(SerializationUtils.serialize(value));
				//long listSize = GraphLayout.parseInstance(compressedValue).totalSize();

				//System.out.println("Memoria utilizada por la lista comprimida: " + listSize + " bytes");
				cache.put(key, compressedValue);
	        }
	    }

	    public Object getFromCache(String cacheName, String key)  {
	        Cache cache = cacheManager.getCache(cacheName);
	        if (cache != null) {
	            byte[] compressedValue = cache.get(key, byte[].class);
				if (compressedValue != null) {
				    return SerializationUtils.deserialize(decompressZstandard(compressedValue));
				}
	        }
	        return null;
	    }

	   
	    
	    private byte[] compressZstandard(byte[] data) {
	        return Zstd.compress(data);
	    }

	    private byte[] decompressZstandard(byte[] compressedData) {
	        return Zstd.decompress(compressedData, (int) Zstd.decompressedSize(compressedData));
	    }
	    
}
