package com.archerswet.rxjava;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.observables.GroupedObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxJavaTest {

    public static void test01(){
        //观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d("MainActivity","onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d("MainActivity","onNext" + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("MainActivity","onError");
            }

            @Override
            public void onComplete() {
                Log.d("MainActivity","onComplete");
            }
        };

        //被观察者
        Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onComplete();
            }
        });

        //Observable observable = Observable.just("我是谁","我在哪");

        observable.subscribe(observer);
    }


    public static void test02(){
        Observable.interval(3, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                Log.d("MainActivity", "interval:" + aLong.intValue());
                if(aLong.intValue() > 5){

                }
            }
        });
    }

    public static void test03(){
        Observable.range(0,5).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.d("MainActivity", "range:" + integer);
            }
        });
    }
    public static void test04(){
        Observable.range(0,3).repeat(2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.d("MainActivity","repeat:" + integer);
            }
        });
    }
    public static void test05(){
        final String Host = "http://**********";
        Observable.just(".cn").map(new Function<String, String>() {

            @Override
            public String apply(String s) throws Throwable {
                return Host + s;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.d("ChangeActivity", "map:" + s);
            }
        });
    }

    //flatMap执行顺序会交错
    public static void test06(){
        final String Host = "http://blog.****.net/";
        List<String> mlist = new ArrayList<>();
        mlist.add("itachi86");
        mlist.add("itachi87");
        mlist.add("itachi88");
        Observable.fromIterable(mlist).flatMap(new Function<String, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(String s) throws Throwable {
                return Observable.just(Host + s);
            }
        }).cast(String.class).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.d("ChangeActivity", "flatMap:" + s);
            }
        });
    }

    //concatMap 和flatMap一致，但能解决交错问题
    public static void test07(){
        final String Host = "http://blog.****.net/";
        List<String> mlist = new ArrayList<>();
        mlist.add("itachi86");
        mlist.add("itachi87");
        mlist.add("itachi88");
        Observable.fromIterable(mlist).concatMap(new Function<String, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(String s) throws Throwable {
                return Observable.just(Host + s);
            }
            //转换为String
        }).cast(String.class).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.d("ChangeActivity", "concatMap:" + s);
            }
        });
    }

    //flatMapIterable 将1，2，3 三个数据封装为iterable
    public static void test08(){
        Observable.just(1,2,3).flatMapIterable(new Function<Integer, Iterable<Integer>>() {
            @Override
            public Iterable<Integer> apply(Integer integer) throws Throwable {
                List<Integer> mlist = new ArrayList<>();
                mlist.add(integer + 1);
                return mlist;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.d("ChageActivity", "flatMapIterable:" + integer);
            }
        });
    }

    //buffer 几个为一组发射
    public static void test09(){
        Observable.just(1,2,3,4,5,6).buffer(3).subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> integers) throws Throwable {
                for(Integer i : integers){
                    Log.d("ChangeActivity", "buffer:" + i);
                }
                Log.d("MainAtivity", "----------------");
            }
        });
    }

    public static class People {
        String name;
        String id;

        public People(String name, String id) {
            this.name = name;
            this.id = id;
        }
    }

    //groupBy 以特定内容分组发射
    public static void test10(){
        People p1 = new People("周杰伦","A");
        People p2 = new People("张震岳","SS");
        People p3 = new People("刘德华","S");
        People p4 = new People("胡一天","S");
        People p5 = new People("林志颖","A");
        People p6 = new People("鲁迅","SS");
        People p7 = new People("胡适","S");
        People p8 = new People("李大钊","A");
        Observable<GroupedObservable<String, People>> GroupedObservable =
                Observable.just(p1,p2,p3,p4,p5,p6,p7,p8).groupBy(new Function<People, String>() {
                    @Override
                    public String apply(People people) throws Throwable {
                        return people.id;
                    }
                });

        Observable.concat(GroupedObservable).subscribe(new Consumer<People>() {
            @Override
            public void accept(People people) throws Throwable {

                Log.d("ChangeActivity", "groupBy:" + people.name + "----" + people.id);
            }
        });
    }

    //filter 过滤操作
    public static void test11(){
        Observable.just(1,2,3,4).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Throwable {
                return integer > 2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.d("FilterActivity","filter:" + integer);
            }
        });
    }

    //只发射第3个数据
    public static void test12(){
        Observable.just(1,2,3,4).elementAt(2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.d("FilterActivity", "elementAt:" + integer);
            }
        });
    }
    //发射不重复的数据
    public static void test13(){
        Observable.just(1,2,2,3,4,1).distinct().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.d("FilterActivity", "distinct:" + integer);
            }
        });
    }
    //跳过2个数据，发射
    public static void test14(){
        Observable.just(1,2,3,4,5,6).skip(2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.d("FilterActivity", "skip:" + integer);
            }
        });
    }
    //只发送前三个数据
    public static void test15(){
        Observable.just(1,2,3,4,5,6).take(2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.d("FilterActivity", "take:" + integer);
            }
        });
    }
    //全都不发射
    public static void test16(){
        Observable.just(1,2,3,4).ignoreElements().subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.d("FilterActivity", "onComplete");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("FilterActivity", "onError");
            }
        });
    }
    //每隔200毫秒 发送第一个数据
    public static void test17(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Throwable{
                for(int i = 0; i < 10; i++){
                    emitter.onNext(i);
                    try{
                        Thread.sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                emitter.onComplete();
            }
        }).throttleFirst(200, TimeUnit.MILLISECONDS).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.d("FilterActivity", "throttleFirst:" + integer);
            }
        });
    }
    //每隔200毫秒发射最后一个数据
    public static void test18(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Throwable{
                for(int i = 0; i <10; i++){
                    emitter.onNext(i);
                    int sleep = 100;
                    if(i % 3 == 0){
                        sleep = 300;
                    }
                    try{
                        Thread.sleep(sleep);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                emitter.onComplete();
            }
        }).throttleWithTimeout(200, TimeUnit.MILLISECONDS).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.d("FilterActivity", "throttleWithTimeOut:" + integer);
            }
        });
    }
    //merge合并发射
    public static void test19(){
        Observable<Integer> obs1 = Observable.just(1,2,3).subscribeOn(Schedulers.io());
        Observable<Integer> obs2 = Observable.just(4,5,6);
        Observable.merge(obs1,obs2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.d("CombineActivity", "merge:" + integer);
            }
        });
    }
    //concat 连接发射
    public static void test20(){
        Observable<Integer> obs3 = Observable.just(1,2,3).subscribeOn(Schedulers.io());
        Observable<Integer> obs4 = Observable.just(4,5,6);
        Observable.concat(obs3,obs4).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.d("CombineActivity", "concat:" + integer);
            }
        });
    }
    //zip 组合发射
    public static void test21(){
        Observable<Integer> obs5 = Observable.just(1,2,3);
        Observable<String> obs6 = Observable.just("a", "b", "c");
        Observable.zip(obs5, obs6, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Throwable{
                return integer + s;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.d("CombineActivity", "zip:" + s);
            }
        });
    }
    //combineLatest 最末尾结合
    public static void test22(){
        Observable<Integer> obs7 = Observable.just(1,2,3);
        Observable<String> obs8 = Observable.just("a", "b", "c");
        Observable.combineLatest(obs7, obs8, new BiFunction<Integer, String, String>() {

            @Override
            public String apply(Integer integer, String s) throws Throwable {
                return integer + s;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.d("CombineActivity", "combineLatest:" + s);
            }
        });
    }
    //delay 发送每段数据前暂停一段时间
    public static void test23(){
        Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Long> emitter) throws Throwable {
                Long currentTime = System.currentTimeMillis() / 1000;
                emitter.onNext(currentTime);
            }
        }).delay(2, TimeUnit.MILLISECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                Log.d("HelpActivity", "delay:" + (System.currentTimeMillis() / 1000 - aLong));
            }
        });
    }
    //do 监听回调函数
    /*
    doOnEach：为Observable注册这样一个回调，Observable每发射一项数据就会调用一个回调函数，包括onNext、onError和onComplete
    doOnNext：只有执行onNext的时候会被调用
    doOnSubscribe：当观察者订阅Observable时就会被调用
    doOnError：当Observable异常终止调用onError时会被调用
    doOnTerminate：当Observable终止（无论正常终止或异常终止）之前会被调用
    finallyDo：当Observable终止（无论正常终止还是异常终止）之后会被调用
     */
    public static void test24(){
        Observable.just(1,2).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.d("HelpActivity","call:" + integer);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.d("HelpActivity", "onNext:" + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("HelpActivity", "Error:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("HelpActivity","onComplete");
            }
        });
    }
    //subscribeOn observeOn
    /*
    subscribeOn操作符用于指定Observable自身在哪个线程上运行，如果Observable需要执行耗时操作，
    则一般可以让其在新开的一个子线程上运行。observeOn用来指定Observe所运行的线程，也就是发射出
    的数据在那个线程上使用。
     */
    public static void test25(){
        Observable<Integer> obs = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                Log.d("HelpActivity", "Observable:" + Thread.currentThread().getName());
                emitter.onNext(1);
                emitter.onComplete();
            }
        });

        obs.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.d("HelpActivity", "Observer:" + Thread.currentThread().getName());
            }
        });
    }

    /*
    如果原始的Observable过了指定的一段时长还没有发射任何数据，
    则timeout操作符会以一个onError通知来终止这个Observable，
    或者继续执行一个备用的Observable。
     */
    public static void test26(){
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                for(int i = 0; i < 4; i++){
                    try{
                        Thread.sleep(i * 100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        }).timeout(200, TimeUnit.MILLISECONDS, Observable.just(10,11));
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.d("HelpActivity", "timeout:" + integer);
            }
        });
    }
    /*
    RxJava将catch实现为3个不同的操作符

    onErrorReturn：返回一个镜像原有Observable行为的新Observable，后者会忽略前者的onError调用，
                    不会将错误传递给观察者，作为替代，它会发射一个特殊的项并调用观察者的onComplete()
                    方法
    onErrorResumeNext：返回一个镜像原有Observable行为的新Observable，后者会忽略前者的onError()
                    调用，不会将错误传递给观察者，作为替代，它会发射备用的Observable
    onExceptionResumeNext：和onErrorResumeNext类似，onExceptionResumeNext()方法返回一个
                    镜像原有Observable行为的新Observable，不同的是，如果onError()收到的
                    Throwable不是一个Exception，就将错误传递给观察者的onError()方法，而
                    不会使用备用的Observable
     */
    public static void test27(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception{
                for(int i = 0; i < 5; i++){
                    if(i == 2){
                        emitter.onError(new Throwable("Throwable"));
                    }
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        }).onErrorReturn(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) throws Exception {
                Log.e("MistakeActivity", "在onErrorReturn处理了: " + throwable.toString());
                return 6;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.i("MistakeActivity", "onNext:" + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("MistakeActivity", "onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i("MistakeActivity", "onComplete");
            }
        });
    }
    public static void test28(){

    }
    public static void test29(){

    }
    public static void test30(){

    }
//    public static void test19(){
//
//    }
//    public static void test19(){
//
//    }
//    public static void test19(){
//
//    }
//    public static void test19(){
//
//    }
//    public static void test19(){
//
//    }
//    public static void test19(){
//
//    }
//    public static void test19(){
//
//    }
//    public static void test19(){
//
//    }
//    public static void test19(){
//
//    }
//    public static void test19(){
//
//    }


}
