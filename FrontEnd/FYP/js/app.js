var collapseTimeline = function (){
        var screensize = Math.max($(window).width(), window.innerWidth),
        expItem = $('.collapse:not(:first)', '#experience');       
        if (screensize < 768){
            expItem.collapse('show');
           
        }
        else{
            expItem.collapse('hide');          
        }
    };
    
    // Call collapseTimeline() when window is loaded.
    $(window).load(function(){
        collapseTimeline();
    });
    
    // Call collapseTimeline() when window is resized.
    $(window).resize(function(){
        collapseTimeline();
    });

    //Animations     
      $('#experience').find('.timeline-item:not(:first)').each(function(i){            
          var element = $(this),
          itemsDelay   = ( isNaN($(this).data('animation-delay')) ? 50 : $(this).data('animation-delay'));
          element.css('opacity', 0).one('inview', function(isInView) {
              if (isInView){
                  setTimeout(function(){
                      element.addClass('animated bounceInUp').css('opacity', 1);
                  } , itemsDelay * (i * 2));
              }
          });
      });