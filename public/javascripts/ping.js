$(document).ready( function() {
  var interval = 4000
  var ping = setInterval( function() {
    $.ajax({
      url: '/ping.txt',
      dataType: 'text',
      success: function (data) {
        $("[data-ping='ping']").append( data )
      }
    })
    }, interval
  )
})
