<?php

function myGenerator() {
    yield "1";
    yield "2";
    yield "3";
}

foreach (myGenerator() as $value) {
    echo $value, PHP_EOL;
}
