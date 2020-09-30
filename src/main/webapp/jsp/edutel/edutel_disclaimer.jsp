<!DOCTYPE HTML>
<html>
<%
	response.setContentType("text/html; charset=UTF-8");
	request.setCharacterEncoding("UTF8");
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.hbs.edutel.common.model.interfaces.IUsers"%>
<%@ page import="com.hbs.edutel.common.model.interfaces.IUserRoles"%>
<%@ page import="com.hbs.edutel.util.CommonUtil"%>
<%@ page import="com.hbs.edutel.util.common.consts.ConstInterface"%>
<%@ page import="com.hbs.edutel.util.common.ConstEnumUtil.*"%>
<%@ page import="java.util.*"%>
<%@ page
	import="com.hbs.edutel.util.common.property.factory.PropFactory"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="edutel_home_init.jsp" />

<body>
	<s:include value="edutel_home_header.jsp">
		<s:param name="menuActive">2</s:param>
	</s:include>
	<span></span>
	<br>
	<div class="wrap">
		<div class="main">
			<div class="about-top">
				<div class="cont1  about_desc">
					<!-- Start main content -->
					<div class="services style-1 home bottom-3"
						style="margin-top: -20px;">
						<div class="container clearfix">
							<div style="margin: 20px; text-align: justify;">
								<div class="banner-box3">
									<span class="text20">&nbsp;&nbsp;Edutel Academy Web Site Agreement</span>
									<p class="link1"></p>
								</div>

								<p class="paraCustom" style="padding: 10px;">The
									www.edutelacademy.com web site (the "site") is an online
									information service provided by Edutel Academy
									("www.edutelacademy.com"), subject to your compliance with the
									terms and conditions set forth below. Please read this document
									carefully before accessing or using the site. By accessing or
									using the site, you agree to be bound by the terms and
									conditions set forth below. If you do not wish to be bound by
									these terms and conditions, you may not access or use the site.
									Www.edutelacademy.com may modify this agreement at any time,
									and such modifications shall be effective immediately upon
									posting of the modified agreement on the site. You agree to
									review the agreement periodically to be aware of such
									modifications and your continued access or use of the site
									shall be deemed your conclusive acceptance of the modified
									agreement.</p>
								<div class="banner-box3">
									<span class="text20">&nbsp;&nbsp;1.Copyright, Licenses & Idea
										Submissions</span>
									<p class="link1"></p>
								</div>

								<p class="paraCustom" style="padding: 10px;">The entire
									contents of the site are protected by international copyright
									and trademark laws. The owner of the copyrights and trademarks
									are www.edutelacademy.com, its affiliates or other third party
									licensors. You may not modify, copy, reproduce, republish,
									upload, post, transmit, or distribute, in any manner, the
									material on the site, including text, graphics, code and/or
									software. You may print and download portions of material from
									the different areas of the site solely for your own
									non-commercial use provided that you agree not to change or
									delete any copyright or proprietary notices from the materials.
									You agree to grant to www.edutelacademy.com a non-exclusive,
									royalty-free, worldwide, perpetual license, with the right to
									sub-license, to reproduce, distribute, transmit, create
									derivative works of, publicly display and publicly perform any
									materials and other information (including, without limitation,
									ideas contained therein for new or improved products and
									services) you submit to any public areas of the site (such as
									bulletin boards, forums and newsgroups) or by e-mail to
									www.edutelacademy.com by all means and in any media now known
									or hereafter developed. You also grant to www.edutelacademy.com
									the right to use your name in connection with the submitted
									materials and other information as well as in connection with
									all advertising, marketing and promotional material related
									thereto. You agree that you shall have no recourse against
									www.edutelacademy.com for any alleged or actual infringement or
									misappropriation of any proprietary right in your
									communications to www.edutelacademy.com.</p>
								<div class="banner-box3">
									<span class="text20">&nbsp;&nbsp;Traffic Server 1.01 Trademarks</span>
									<p class="link1"></p>
								</div>

								<p class="paraCustom" style="padding: 10px;">Publications,
									products, content or services referenced herein or on the site
									are the exclusive trademarks or service marks of
									www.edutelacademy.com. Other product and company names
									mentioned in the site may be the trademarks of their respective
									owners.</p>
								<div class="banner-box3">
									<span class="text20">&nbsp;&nbsp;2. Use of the Site</span>
									<p class="link1"></p>
								</div>

								<p class="paraCustom" style="padding: 10px;">You understand
									that, except for information, products or services clearly
									identified as being supplied by www.edutelacademy.com,
									www.edutelacademy.comdoes not operate, control or endorse any
									information, products or services on the internet in any way.
									Except for www.edutelacademy.com- identified information,
									products or services, all information, products and services
									offered through the site or on the internet generally are
									offered by third parties, that are not affiliated with
									www.edutelacademy.com a. You also understand that
									www.edutelacademy.com cannot and does not guarantee or warrant
									that files available for downloading through the site will be
									free of infection or viruses, worms, trojan horses or other
									code that manifest contaminating or destructive properties. You
									are responsible for implementing sufficient procedures and
									checkpoints to satisfy your particular requirements for
									accuracy of data input and output, and for maintaining a means
									external to the site for the reconstruction of any lost data.
									You assume total responsibility and risk for your use of the
									site and the internet. www.edutelacademy.com provides the site
									and related information "as is" and does not make any express
									or implied warranties, representations or endorsements
									whatsoever (including without limitation warranties of title or
									noninfringement, or the implied warranties of merchantability
									or fitness for a particular purpose) with regard to the
									service, any merchandise information or service provided
									through the service or on the internet generally, and
									www.edutelacademy.com shall not be liable for any cost or
									damage arising either directly or indirectly from any such
									transaction. It is solely your responsibility to evaluate the
									accuracy, completeness and usefulness of all opinions, advice,
									services, merchandise and other information provided through
									the service or on the internet generally. Www.edutelacademy.com
									does not warrant that the service will be uninterrupted or
									error-free or that defects in the service will be corrected.
									You understand further that the pure nature of the internet
									contains unedited materials some of which are sexually explicit
									or may be offensive to you. Your access to such materials is at
									your risk. www.edutelacademy.com has no control over and
									accepts no responsibility whatsoever for such materials.</p>
								<div class="banner-box3">
									<span class="text20">&nbsp;&nbsp;Limitation of Liability</span>
									<p class="link1"></p>
								</div>
								<h4
									style="padding-top: 5px; font-family: 'Open Sans'; color: #076AA6;"></h4>
								<p class="paraCustom" style="padding: 10px;">In no event
									will www.edutelacademy.com be liable for
								<pclass="paraCustom" style="padding: 10px;">(i) Any incidental, consequential, or indirect damages
									(including, but not limited to, damages for loss of profits,
									business interruption, loss of programs or information, and the
									like) arising out of the use of or inability to use the
									service, or any information, or transactions provided on the
									service, or downloaded from the service, or any delay of such
									information or service. Even if www.edutelacademy.com or its
									authorized representatives have been advised of the possibility
									of such damages, or</p>
								<p class="paraCustom" style="padding: 10px;">
									(ii) Any claim attributable to errors, omissions, or other
									inaccuracies in the service and/or materials or information
									downloaded through the service. Because some states do not
									allow the exclusion or limitation of liability for
									consequential or incidental damages, the above limitation may
									not apply to you. In such states, www.edutelacademy.com
									liability is limited to the greatest extent permitted by law.<br>
									<br> www.edutelacademy.com makes no representations
									whatsoever about any other web site which you may access
									through this one or which may link to this Site. When you
									access a non-www.edutelacademy.com web site, please understand
									that it is independent from www.edutelacademy.com, and that
									www.edutelacademy.com has no control over the content on that
									web site. In addition, a link to a www.edutelacademy.com web
									site does not mean that www.edutelacademy.com endorses or
									accepts any responsibility for the content, or the use, of such
									web site.
								</p>
								<div class="banner-box3">
									<span class="text20">&nbsp;&nbsp;3.Indemnification</span>
									<p class="link1"></p>
								</div>

								<p class="paraCustom" style="padding: 10px;">You agree to
									indemnify, defend and hold harmless www.edutelacademy.com, its
									officers, directors, employees, agents, licensors, suppliers
									and any third party information providers to the Service from
									and against all losses, expenses, damages and costs, including
									reasonable attorneys' fees, resulting from any violation of
									this Agreement (including negligent or wrongful conduct) by you
									or any other person accessing the Service.</p>
								<div class="banner-box3">
									<span class="text20">&nbsp;&nbsp;4.Third Party Rights</span>
									<p class="link1"></p>
								</div>

								<p class="paraCustom" style="padding: 10px;">The provisions
									of paragraphs 2 (Use of the Service), and 3 (Indemnification)
									are for the benefit of www.edutelacademy.com and its officers,
									directors, employees, agents, licensors, suppliers, and any
									third party information providers to the Service. Each of these
									individuals or entities shall have the right to assert and
									enforce those provisions directly against you on its own
									behalf.</p>
								<div class="banner-box3">
									<span class="text20">&nbsp;&nbsp;5.Terms Termination</span>
									<p class="link1"></p>
								</div>

								<p class="paraCustom" style="padding: 10px;">This Agreement
									may be terminated by either party without notice at any time
									for any reason. The provisions of paragraphs
								<p class="paraCustom" style="padding: 5px;">1. Copyright, Licenses and Idea Submissions</p>
								<p class="paraCustom" style="padding: 5px;">2. Use of the Service</p>
								<p class="paraCustom" style="padding: 5px;">3. Indemnification</p>
								<p class="paraCustom" style="padding: 5px;">4. Third Party Rights and</p>
								<p class="paraCustom" style="padding: 5px;">5. Miscellaneous shall survive any termination of this
									Agreement.</p>
								<div class="banner-box3">
									<span class="text20">&nbsp;&nbsp;Miscellaneous</span>
									<p class="link1"></p>
								</div>

								<p class="paraCustom" style="padding: 10px;">This Agreement
									shall all be governed and construed in accordance with the laws
									of india applicable to agreements made and to be performed in
									india. You agree that any legal action or proceeding between
									www.edutelacademy.com and you for any purpose concerning this
									Agreement or the parties' obligations hereunder shall be
									brought exclusively in a federal or state court of competent
									jurisdiction sitting in india . Any cause of action or claim
									you may have with respect to the Service must be commenced
									within one (1) year after the claim or cause of action arises
									or such claim or cause of action is barred.
									www.edutelacademy.com's failure to insist upon or enforce
									strict performance of any provision of this Agreement shall not
									be construed as a waiver of any provision or right. Neither the
									course of conduct between the parties nor trade practice shall
									act to modify any provision of this Agreement.
									www.edutelacademy.com may assign its rights and duties under
									this Agreement to any party at any time without notice to you.
									Any rights not expressly granted herein are reserved - Edutel
									Academy.</p>
							</div>
						</div>
						<!-- End Container -->
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<s:include value="edutel_home_footer.jsp" />
</body>
</html>