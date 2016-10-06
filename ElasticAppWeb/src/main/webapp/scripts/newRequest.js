/**
 * Created by sudha on 06-Oct-16.
 */
$(function () {
    if($('#errorForm').val() == 'false'){
        $('input[type=text]').each(function (index, element) {
           element.value = '';
        });

    }
});