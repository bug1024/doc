# 软件工程

## 设计原则
 - 开-闭原则(Open-Closed Principle, OCP)：对扩展开放,对修改关闭。
 - 里氏替换原则(Liskov Substitution Principle, LSP)：子类可以替换基类
 - 依赖倒置原则(Dependence Inversion Principle)：抽象不应当依赖于细节,细节应当依赖于抽象；针对接口编程
 - 接口隔离原则(Interface Segregation Principle, ISP)：一个类对另外一个类的依赖是建立在最小的接口上；使用多个专门的接口比使用单一的总接口要好
 - 单一职责原则(Simple responsibility pinciple, SRP)：每个类只负责单一的职责，多余的职责应该分离出去
 - 合成/聚合复用原则(Composite/Aggregate Reuse Principle,CARP)：尽量使用聚合，而不是继承
 - 迪米特法则(Law of Demeter LoD)又叫做最少知识原则(Least Knowledge Principle,LKP)：一个对象应当对其他对象有尽可能少的了了解；如果两个类不必彼此直接通信,那么这两个类就不应当发生直接的相互作用,如果其中的一个类需要调用另一个类的某一个方法的话,可以通过第三者转发这个调用
