$(document).ready( function() {
  var ping = setInterval( function() { txtPing() }, 1000)
  function txtPing() {
    $.ajax({
      url: '/ping.txt',
      dataType: 'text',
      success: function (data) {
        $("[data-ping='ping']").append( data )
      }
    })
  }
})
