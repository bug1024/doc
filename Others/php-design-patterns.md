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

## 命令行模式
```
    $receiver = new Receiver();
    $command = new MyCommand($receiver);
    $invoker = new Invoker($command);
    $invoker->invoke();
```

## 外观模式
 外部与一个子系统的通信必须通过一个统一的外观对象进行，为子系统中的一组接口提供一个一致的界面，外观模式定义了一个高层接口，这个接口使得这一子系统更加容易使用。外观模式又称为门面模式，它是一种对象结构型模式。

## 享元模式
 它使用共享物件，用来尽可能减少内存使用量以及分享资讯给尽可能多的相似物件；它适合用于当大量物件只是重复因而导致无法令人接受的使用大量内存。

## 代理模式
 所谓的代理者是指一个类别可以作为其它东西的接口

## 中介者模式
 用一个中介对象来封装一系列的对象交互。中介者使各对象不需要显式地相互引用，从而使其耦合松散，而且可以独立地改变它们之间的交互。

## 观察者模式
 个目标对象管理所有相依于它的观察者对象，并且在它本身的状态改变时主动发出通知。

## 状态模式
 允许一个对象在其内部状态改变时改变它的行为

## 策略模式
 定义一系列算法，将每一个算法封装起来，并让它们可以相互替换。策略模式让算法独立于使用它的客户而变化，也称为政策模式(Policy)。
