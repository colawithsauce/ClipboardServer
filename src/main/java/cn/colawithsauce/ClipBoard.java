package cn.colawithsauce;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ClipBoard {
  // 互斥访问资源
  private String clipboardContents = "";

  // 单例模式
  private static final ClipBoard instance = new ClipBoard();
  private ClipBoard() {}
  public static ClipBoard getInstance() {
    return instance;
  }

  // 读写锁
  private final ReadWriteLock lock = new ReentrantReadWriteLock();
  private final Lock rLock = lock.readLock();
  private final Lock wLock = lock.writeLock();

  public String getClipboardContents() {
    rLock.lock();
    String ret = this.clipboardContents;
    rLock.unlock();
    return ret;
  }

  public void setClipboardContents(String s) {
    wLock.lock();
    this.clipboardContents = s;
    wLock.unlock();
  }
}
