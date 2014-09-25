/* Chinese initialisation for the jQuery UI date picker plugin. */
/* Written by Cloudream (cloudream@gmail.com). */
jQuery(function ($) {
    $.datepicker.regional['zh-CN'] = {
        closeText: '\u5173\u95ed',
        prevText: '\u8fc7\u53bb',
        nextText: '\u5c06\u6765',
        currentText: '\u5f53\u524d\u65f6\u95f4',
        monthNames: ['\u4e00\u6708', '\u4e8c\u6708', '\u4e09\u6708', '\u56db\u6708', '\u4e94\u6708', '\u516d\u6708',
                     '\u4e03\u6708', '\u516b\u6708', '\u4e5d\u6708', '\u5341\u6708', '\u5341\u4e00\u6708', '\u5341\u4e8c\u6708'],
        monthNamesShort: ['\u4e00', '\u4e8c', '\u4e09', '\u56db', '\u4e94', '\u516d',
                          '\u4e03', '\u516b', '\u4e5d', '\u5341', '\u5341\u4e00', '\u5341\u4e8c'],
        dayNames: ['\u661f\u671f\u4e00', '\u661f\u671f\u4e8c', '\u661f\u671f\u4e09', '\u661f\u671f\u56db', '\u661f\u671f\u4e94', '\u661f\u671f\u516d', '\u661f\u671f\u5929'],
        dayNamesShort: ['\u5468\u4e00', '\u5468\u4e8c', '\u5468\u4e09', '\u5468\u56db', '\u5468\u4e94', '\u5468\u516d', '\u5468\u5929'],
        dayNamesMin: ['\u4e00', '\u4e8c', '\u4e09', '\u56db', '\u4e94', '\u516d', '\u65e5'],
        weekHeader: '\u5468',
        dateFormat: 'yy-mm-dd',
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: true,
        yearSuffix: '\u5e74'
    };
    $.datepicker.setDefaults($.datepicker.regional['zh-CN']);
});