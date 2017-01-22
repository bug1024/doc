# PHP设计模式

## 简单工厂模式
```
    $cat = Factory::createAnimal('cat');
```

## 工厂方法模式
```
    $cat = CatFactory::createAnimal();
    $cat->say();
    $cat->run();

    $dog = DogFactory::createAnimal();
    $dog->say();
    $dog->run();
```

## 抽象工厂模式
```
    newTv = ProductFactory::createTV();
    $newTv->open();
    $newTv->watch();

    $newPc = ProductFactory::createPc();
    $newPc->work();
    $newPc->play();
```

## 建造者模式
```
    $carBuilder = new CarBuilder();
    $director = new Director();
    $director->setBuilder($carBuilder);
    $newCar = $director->startBuild();
    $newCar->show();
```

## 单例模式
```
    $obj = Singleton::getInstance();
```

## 适配器模式
```
    $adaptee = new Adaptee();
    $target = new Adapter($adaptee);
    $target->request();
```

## 装饰者模式
 即将一个类的对象嵌入另一个对象中，由另一个对象来决定是否调用嵌入对象的行为以便扩展自己的行为，我们称这个嵌入的对象为装饰器(Decorator)

## 桥接模式
 它把事物对象和其具体行为、具体特征分离开来，使它们可以各自独立的变化
