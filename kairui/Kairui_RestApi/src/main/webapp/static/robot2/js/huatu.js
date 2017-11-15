(function($){
	$.fn.huatu = function(options){
		var settings = $.extend({
			foregroundColor: "#556b2f",
            backgroundColor: "#eee",
            fillColor: false,
            width: 15,
            dimension: 200,
            size: 15, 
			percent: 50,
            animationStep: 1.0
		},options);
		return this.each(function(){
            var dimension = '';
            var text = '';
			var info = '';
            var width = '';
            var size = 0;
			var percent = 0;
			var endPercent = 100;
			var fgcolor = '';
			var bgcolor = '';
			var icon = '';
            var animationstep = 0.0;

            $(this).addClass('huatu');

            if($(this).data('dimension') != undefined) {
                    dimension = $(this).data('dimension');
                } else {
                    dimension = settings.dimension;
                }
    
                if($(this).data('width') != undefined) {
                    width = $(this).data('width');
                } else {
                    width = settings.width;
                }
    
                if($(this).data('fontsize') != undefined) {
                    size = $(this).data('fontsize');
                } else {
                    size = settings.size;
                }
				
				if($(this).data('percent') != undefined) {
                    percent = $(this).data('percent') / 100;
					endPercent = $(this).data('percent');
                } else {
                    percent = settings.percent / 100;
                }
				
				if($(this).data('fgcolor') != undefined) {
                    fgcolor = $(this).data('fgcolor');
                } else {
                    fgcolor = settings.foregroundColor;
                }
				
				if($(this).data('bgcolor') != undefined) {
                    bgcolor = $(this).data('bgcolor');
                } else {
                    bgcolor = settings.backgroundColor;
                }
				
                if($(this).data('animation-step') != undefined) {
                    animationstep = parseFloat($(this).data('animation-step'));
                } else {
                    animationstep = settings.animationStep;
                }
                if($(this).data('text') != undefined) {
                    text = $(this).data('text');
					
					if($(this).data('icon') != undefined) {
						icon = '<i class="fa ' + $(this).data('icon') + '"></i>';
					}
					
					 if($(this).data('type') != undefined) {
						type = $(this).data('type');
					
						if(type == 'half') {
							$(this).append('<span class="circle-text-half">' +  icon  + text + '</span>');
							$(this).find('.circle-text-half').css({'line-height': (dimension / 1.45) + 'px', 'font-size' : size + 'px' });
						} else {
							$(this).append('<span class="circle-text">' + icon + text + '</span>');
							$(this).find('.circle-text').css({'line-height': dimension + 'px', 'font-size' : size + 'px' });
						}
					} else {
						$(this).append('<span class="circle-text">' + icon + text + '</span>');
						$(this).find('.circle-text').css({'line-height': dimension + 'px', 'font-size' : size + 'px' });
					}
                } else if($(this).data('icon') != undefined) {
				
				}
				
				if($(this).data('info') != undefined) {
                    info = $(this).data('info');
					
					if($(this).data('type') != undefined) {
						type = $(this).data('type');
					
						if(type == 'half') { 
							$(this).append('<span class="circle-info-half">' + info + '</span>');
							$(this).find('.circle-info-half').css({'line-height': (dimension * 0.9) + 'px', });
						} else {
							$(this).append('<span class="circle-info">' + info + '</span>');
							$(this).find('.circle-info').css({'line-height': (dimension * 1.25) + 'px', });
						}
					} else {
						$(this).append('<span class="circle-info">' + info + '</span>');
						$(this).find('.circle-info').css({'line-height': (dimension * 1.25) + 'px', });
					}
                }
    
                $(this).width(dimension + 'px');

                var canvas = $('<canvas></canvas>').attr({ width: dimension, height: dimension }).appendTo($(this)).get(0);
                var context = canvas.getContext('2d');
                var x = canvas.width / 2;
              	var y = canvas.height / 2;
              	var degrees = percent * 360.0;
              	var radians = degrees * (Math.PI / 180);
              	var radius = canvas.width / 2.5;
              	var startAngle = 2.3 * Math.PI;
              	var endAngle = 0;
              	var counterClockwise = false;
              	var curPerc = animationstep === 0.0 ? endPercent : 0.0;
              	var curStep = Math.max(animationstep, 0.0);
              	var circ = Math.PI * 1.5;
			  	var quart = Math.PI / 2;
			  	var type = '';
			  	var fill = false;

			  	if($(this).data('fill') != undefined) {
					fill = $(this).data('fill');
				} else {
					fill = settings.fillColor;
				}

				function animate(current){
					context.clearRect(0, 0, canvas.width, canvas.height);
					context.beginPath();
					context.arc(x, y, radius, 0.75 * Math.PI, 2.25 * Math.PI, false);
					context.lineWidth = width - 1;
					context.strokeStyle = bgcolor;
					context.stroke();

					context.beginPath();
					context.arc(x + radius / Math.sqrt(2), y + radius / Math.sqrt(2), width / 2, 0, 2 * Math.PI, false);
					context.lineWidth = width;
					context.fillStyle = bgcolor;	
					context.fill();

					context.beginPath();
					context.arc(x - radius / Math.sqrt(2), y + radius / Math.sqrt(2), width / 2, 0, 2 * Math.PI, false);
					context.lineWidth = width;
					if (percent == 0) {
						context.fillStyle = bgcolor;
					}else{
						context.fillStyle = fgcolor;
					}
					context.fill();
					
					if(fill) {
						context.fillStyle = fill;
						context.fill();
					}
					context.beginPath();
					context.arc(x, y, radius, 0.75 * Math.PI, ((circ) * current) + 0.75 * Math.PI, false);
					context.lineWidth = width;
					context.strokeStyle = fgcolor;
					context.stroke();
					console.log(curPerc < endPercent);
					if (curPerc < endPercent) {
	  				     curPerc += curStep;
						 requestAnimationFrame(function () {
							 animate(Math.min(curPerc, endPercent) / 100);
						 });
					}
					if (curPerc == endPercent && percent == 1) {
						context.beginPath();
						context.arc(x + radius / Math.sqrt(2), y + radius / Math.sqrt(2), width / 2, 0, 2 * Math.PI, false);
						context.lineWidth = width;
						context.fillStyle = fgcolor;	
						context.fill();
					}
				}

				animate(curPerc / 100);

		})
	}
}(jQuery));