<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<div class="card card-warning">
	<div class="card-header">
		<h3 class="card-title">General Elements</h3>
	</div>

	<div class="card-body">
		<form:form action="/springform/registerForm05Post" method="post"
		 	modelAttribute="generalElementsVO">
			<div class="row">
				<div class="col-sm-6">

					<div class="form-group">
						<label>Text</label>
						<form:input path="text" class="form-control" placeholder="Enter ..." />
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label>Text Disabled</label> 
						<form:input path="textDiabled"
							class="form-control" 
							placeholder="Enter ..." disabled="true" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6">

					<div class="form-group">
						<label>Textarea</label>
						<form:textarea path="textarea" class="form-control"
						 rows="3" cols="10"
						 placeholder="Enter ..."></form:textarea>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label>Textarea Disabled</label>
						<form:textarea path="textareaDisabled" class="form-control" 
						 rows="3" cols="10" 
						 placeholder="Enter ..."
						 disabled="true"></form:textarea>
					</div>
				</div>
			</div>

			<div class="form-group">
				<form:label class="col-form-label" path="inputWithSuccess"><i
					class="fas fa-check"></i> Input with success</form:label>
					 <form:input path="inputWithSuccess" 
					  class="form-control is-valid" 
					placeholder="Enter ..." />
			</div>
			<div class="form-group">
				<form:label class="col-form-label" path="inputWithWarning"><i
					class="far fa-bell"></i> Input with warning</form:label>
					<form:input path="inputWithWarning"
					class="form-control is-warning"
					placeholder="Enter ..." />
			</div>
			<div class="form-group">
				<form:label class="col-form-label" path="inputWithError"><i
					class="far fa-times-circle"></i> Input with error</form:label>
					<form:input path="inputWithError" class="form-control is-invalid" 
					placeholder="Enter ..." />
			</div>
			<div class="row">
				<div class="col-sm-6">


					<div class="form-group">
						<div class="form-check">
							<!-- model.addAttribute("checkboxMap",checkboxMap); -->
							<!-- path는 변수명과 맞추고, items의 달러{}안에 들어가는 내용은 속성값과 맞춰야함  -->
							<form:checkboxes path="checkbox"  items="${checkboxMap}" 
								class="form-check-input" /> 
						</div>
					</div>
				</div>
				<div class="col-sm-6">

					<div class="form-group">
						<div class="form-check">
							<form:radiobutton path="radio" value="jjajang" label="짜장면" 
							class="form-check-input" />
						</div>
						<div class="form-check">
							<form:radiobutton path="radio" value="jjamppong" label="짬뽕" 
							class="form-check-input" checked="true" />
						</div>
						<div class="form-check">
							<form:radiobutton path="radio" value="tangsuyuk" label="탕수육" 
							class="form-check-input" disabled="true" />
						</div>
					</div>
				</div>
			</div>
         <div class="row">
            <div class="col-sm-6">

               <div class="form-group">
                  <label>Select</label> 
                  <!-- model.addAttribute("selectMap",selectMap); -->
                  <form:select path ="select" class="form-control">
                     <form:option value=" " label="==선택해주세요==" />
                     <form:options items="${selectMap}" />
                  </form:select>
               </div>
            </div>

				<div class="col-sm-6">
					<div class="form-group">
						<label>Select Disabled</label> 
						<!-- model.addAttribute("selectDisabledMap",selectDisabledMap); -->
						 <form:select path="selectDisabled" class="form-control" disabled="true">
							<form:option value="" label="==선택 하세요==" />
							<form:options items="${selectDisabledMap}" />
						</form:select>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-sm-6">
					<div class="form-group">
						<label>Select Multiple</label> 
						<!-- model.addAttribute("selectMultipelMap",selectMultipelMap); -->
						<form:select path="selectMultiple" multiple="true"
							class="form-control">
							<form:option value="" label="===선택해주세요===" />
							<form:options items="${selectMultipelMap}"/>
						</form:select>
					</div>
				</div>
				
				<div class="col-sm-6">
					<div class="form-group">
						<label>Select Multiple Disabled</label> 
						<form:select path="selectMultipleDisabled" multiple="true"
							class="form-control" disabled="true">
							<form:option value="" label="===선택해주세요===" />
							<form:options items="${selectMultipleDisabledMap}"/>
						</form:select>
					</div>
				</div>
				
			</div>
			<form:button name="register">등록</form:button>
		</form:form>
	</div>

</div>