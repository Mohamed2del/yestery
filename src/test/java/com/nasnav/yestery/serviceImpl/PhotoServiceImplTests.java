package com.nasnav.yestery.serviceImpl;

import com.nasnav.yestery.service.PhotoService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PhotoServiceImplTests {

	@Autowired
	PhotoService photoService;


	@Test
	void approvePhotoTest() throws Exception {

		Assert.assertNotNull(photoService.approvePhoto(1L));
	}

	@Test
	void rejectPhotoTest() throws Exception {

		Assert.assertNotNull(photoService.rejectPhoto(1L));
	}



}
