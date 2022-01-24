package agh.ics.oop.observers;

public interface IObservable {
    void addObserver(IObserver observer);

    void removeObserver(IObserver observer);
}
