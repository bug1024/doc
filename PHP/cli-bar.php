<?php

// 获取x, y
function getXY($points) {
    $xSize = count($points);
    $temp = [];
    foreach ($points as $v) {
        $temp[] = $v[1];
    }
    rsort($temp);
    $ySize = $temp[0];

    return ['x' => $xSize, 'y' => $ySize];
}

// 获取画布
function getMap($xSize, $ySize) {
    $map = [];
    for ($m = $ySize; $m > 0; $m--) {
        for ($n = 0; $n < $xSize; $n++) {
            $map[] = $n . '-' . $m;
        }
    }

    return $map;
}

// 获取有效点集合
function getSet($points) {
    $set = [];
    foreach ($points as $k => $p) {
        for ($i = $p[1]; $i > 0; $i--) {
            $set[] = $k . '-' . $i;
        }
    }

    return $set;
}

// 绘画工具
function spot() {
    echo "=     ";
}
function blank() {
    echo "      ";
}
function enter() {
    echo "\n";
}

// 绘制
function create($map, $set, $xSize) {
    foreach ($map as $k => $value) {
        if (in_array($value, $set)) {
            spot();
            list($n) = explode('-', $value);
            if ($n == $xSize - 1) {
                enter();
            }
        } else {
            blank();
            if ($value[0] == $xSize - 1) {
                enter();
            }
        }
    }

}

function draw($points) {
    $xy  = getXY($points);
    $map = getMap($xy['x'], $xy['y']);
    $set = getSet($points, $xy['x']);
    create($map, $set, $xy['x']);
}

// test

$points = [
    [1, 2],
    [1, 2],
    [1, 5],
    [1, 5],
    [1, 5],
    [1, 4],
    [1, 2],
    [1, 4],
    [1, 5],
    [1, 5],
];


draw($points);

