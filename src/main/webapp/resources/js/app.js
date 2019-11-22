"use strict"
var app = app || {}
app=(()=>{
	let _, js
	let init=()=>{
		_=$.ctx()
		js=$.js()
	}
	let run=x=>{
		$.getScript(x+'/resources/js/cmm/router.js', ()=>{
			$.extend(new Session(x))
			init()
			onCreate()
		})
	}
	let onCreate=()=>{
		alert('하이염 ㅎㅎ')
		setContentView()
	}
	let setContentView=()=>{
		$('<table id="tab"><tr></tr></table>')
		.css({
			width : '80%',
			height : '80%',
			border : '1px solid black',
			margin : '0 auto' 
		})
		.appendTo('#wrapper')
		$('<td/>', {id : 'left'})
		.css({
			width : '20%',
			height : '800px',
			border : '1px solid black',
			'vertical-align' : 'top'
		})
		.appendTo('tr')
		$('<td/>', {id : 'right'})
		.css({
			width : '80%',
			height : '100%',
			border : '1px solid black'
		})
		.appendTo('tr')
		
		$.each(['NAVER','CGV','BUGS'],(i,j)=>{
			$('<div/>')
			.text(j)
			.css({
				width : '100%',
				height : '50px',
				border : '1px solid black',
				'text-align' : 'center'
			})
				.appendTo('#left')
				.click(function(){
					$(this).css({'background-color':'yellow'})
					$(this).siblings().css({'background-color' : 'white'})
					
					switch($(this).text()){
					case 'NAVER' :
						$.getJSON(sessionStorage.getItem('ctx')+'/crawls/naver', d=>{
							$('#right').empty()
							$.each(d, (i,j)=>{
								$('<div>')
								.html('<h1>'+j.origin+'<h1><h4>'+j.trans+'</h4>')
								.css({
									width : '40%',
									height : '40%',
									border : '3px solid red',
									float : 'left'
								})
								.appendTo('#right')
							})
						})
						break;
					case 'CGV' :
						$.getJSON(sessionStorage.getItem('ctx')+'/crawls/cgv', d=>{
							$('#right').empty()
							$.each(d, (i,j)=>{
								$('<div><img style ="width : 200px;" src = "'+j.photo+'"/><br/>'+j.title+'<br/>'+j.percent+'<br/>'+'<br/>'+j.textInfo+'</div>')
								.css({
									border : '3px solid red',
									float : 'left'
								})
								.appendTo('#right')
							})
						})
						break;
					case 'BUGS' :
						$.getJSON(sessionStorage.getItem('ctx')+'/crawls/bugs', d=>{
							$('#right').empty()
							$('<table id="content"><tr id="head"></tr></table>')
							.css({
								width : '100%',
								height : '80px',
								border : '1px solid red'})
							.appendTo('#right')
							
							$.each(['No.','썸네일','제목','가수'], (i,j)=>{
								$('<th/>')
								.html('<b>'+j+'</b>')
								.css({
									width : '25%',
									height : '100%',
									border : '1px solid red',
									'text-align' : 'center'})
								.appendTo('#head')
							})
							
							$.each(d, (i,j)=>{
								$('<tr><td>'+j.seq+'</td><td><img src="'+j.thumbnail+'"/></td><td>'+j.title+'</td><td>'+j.artist+'</td></tr>')
								.css({
									width : '25%',
									height : '100%',
									border : '1px solid red'
								})
								.appendTo('#content tbody')
							})
						})
						break
					}
				})
		})
		}
	return {run}
})()