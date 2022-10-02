    $(document).ready(function(){
    var server = "https://avantesb.su/nasa";
    var rover = "opportunity";
    var json = null;

    $.ajaxSetup({
        async: false
      });

    $.getJSON(server + "/api/manifest/"+rover, function(data){
        json = data;
      });

  var sols = json.sol;
  var maxSol = json.maxSol;
  var lostSols = json.lostSols;
  var sol;
  var successSol;
  var cameras;
  var txt = $('#sol').val;
  $('#sol').attr("placeholder", "max Sol " + maxSol);

        

        function validation(text_value){
          if (text_value == "") {
            formInvalid();
          } else if(!isNumber(text_value)){
            formInvalid();
          } else if (parseInt(text_value) > maxSol){
            formInvalid();
          } else if($.inArray(text_value, lostSols) > -1) {
            formInvalid();
          } else {
              formValid(text_value);
          }
        }

        $("#sol").on("input", function(){
          
          var text=$(this).val();
          validation(text);
          
        });

        function isLetter(str) {
          return str.match(/[a-z]/i);
        }

        function isNumber(str) {
          return /^(0|[1-9]\d*)$/.test(str);
        }


        $('#sol').blur(function(){
          var text=$(this).val();
          validation(text);
        });

        function formInvalid() {
                    $("#sol").addClass("validate invalid");
                    $("#camera").prop('disabled', true);
                    $("#postButton").attr('disabled', " ");
                    $('select').formSelect();
          }

          function formValid(text_value) {
                    $("#sol").removeClass("validate invalid");
                    $("#sol").addClass("validate valid");
                    $("#camera").prop('disabled', false);
                    $("#postButton").removeAttr('disabled', " ");
                    successSol = text_value;
                    sol = jQuery.grep(sols, function (sols) {return sols.sol == successSol });
                    cameras = sol[0].cameras;
                    $("#camera option").prop('disabled', true);

                    $.each(cameras, function(index, camera){
                      $("#camera option[value="+camera+"]").prop('disabled', false);
                    });
                                        
                    $('select').formSelect();
          }

        $(function(){
        $("#postButton").bind('click', function(){
            $.post(server + "/api/data?rover="+rover, $("#myform").serialize(),
                function(image) {
                       Galleria.run('.galleria', {
                       dataSource: image
                       });
                       galleriaLazyLoadConfig(2, 2);
                        
                });
        });
    });

    });

 
function galleriaLazyLoadConfig(start, next){
    var startLoadArray = start;
    var nextLoad = next;
    Galleria.ready(function(){
          
        var arr = Array.from(Array(startLoadArray)).map((e,i)=>i+1)
        var datalength = this.getDataLength();
        var count = nextLoad;
          
        this.lazyLoad(arr);
        
        this.bind("loadstart", function(e) {
          
            if ( e.index === count ) {
              
            arr = Array.from(Array(nextLoad)).map((e,i) => i+arr[arr.length-1]+1);
              
            this.lazyLoad( arr, function() {
                count = count + nextLoad;
                });
            }
        });
    
    });
}