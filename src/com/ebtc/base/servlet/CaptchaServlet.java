package com.ebtc.base.servlet;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.patchca.color.ColorFactory;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.filter.predefined.DiffuseRippleFilterFactory;
import org.patchca.filter.predefined.DoubleRippleFilterFactory;
import org.patchca.filter.predefined.MarbleRippleFilterFactory;
import org.patchca.filter.predefined.WobbleRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;

import com.ebtc.common.constants.Constants;

public class CaptchaServlet extends HttpServlet {

	private static final long serialVersionUID = 3796351198097771007L; 
	private static ConfigurableCaptchaService captchaService= null;
	private static ColorFactory colorFactory = null;
	private static RandomWordFactory wordFactory = null;
	private static Random random = new Random();
	private static CurvesRippleFilterFactory cuvesRippleFilterFactory = null;
	private static MarbleRippleFilterFactory marbleRippleFilterFactory = null;
	private static DoubleRippleFilterFactory doubleRippleFilterFactory = null;
	private static WobbleRippleFilterFactory wobbleRippleFilterFactory = null;
	private static DiffuseRippleFilterFactory diffuseRippleFilterFactory = null;

	@Override
	public void init() throws ServletException {
		super.init();
		captchaService = new ConfigurableCaptchaService();
		colorFactory = new SingleColorFactory(new Color(25, 60, 170));
		wordFactory = new RandomWordFactory();
		cuvesRippleFilterFactory = new CurvesRippleFilterFactory(captchaService.getColorFactory());
		doubleRippleFilterFactory = new DoubleRippleFilterFactory();
		wobbleRippleFilterFactory = new WobbleRippleFilterFactory();
		diffuseRippleFilterFactory = new DiffuseRippleFilterFactory();
		marbleRippleFilterFactory = new MarbleRippleFilterFactory();
		captchaService.setWordFactory(wordFactory);
		captchaService.setColorFactory(colorFactory);
		captchaService.setWidth(120);
		captchaService.setHeight(50);
	}

	@Override
	public void destroy() {
		wordFactory = null;
		colorFactory = null;
		captchaService = null;
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/png");
		response.setHeader("cache", "no-cache");
		wordFactory.setMaxLength(5);
		wordFactory.setMinLength(3);
		HttpSession session = request.getSession(true);
		OutputStream os = response.getOutputStream();
		switch (random.nextInt(5)) {
		case 0:
			captchaService.setFilterFactory(cuvesRippleFilterFactory);
			break;
		case 1:
			captchaService.setFilterFactory(marbleRippleFilterFactory);
			break;
		case 2:
			captchaService.setFilterFactory(doubleRippleFilterFactory);
			break;
		case 3:
			captchaService.setFilterFactory(wobbleRippleFilterFactory);
			break;
		case 4:
			captchaService.setFilterFactory(diffuseRippleFilterFactory);
			break;
		}
		String captcha = EncoderHelper.getChallangeAndWriteImage(captchaService, "png", os);
		session.setAttribute(Constants.CAPTCHA, captcha);
		os.flush();
		os.close();
	}
}