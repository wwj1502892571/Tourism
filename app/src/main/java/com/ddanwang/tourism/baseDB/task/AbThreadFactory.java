/*
 * Copyright (C) 2012 www.amsoft.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ddanwang.tourism.baseDB.task;

import android.os.Process;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
// TODO: Auto-generated Javadoc

/**
 * © 2012 amsoft.cn
 * 名称：AbThreadFactory.java 
 * 描述：线程工厂.
 *
 * @author 还如一梦中
 * @version v1.0
 * @date：2011-11-10 下午11:52:13
 */
public class AbThreadFactory {
	
	/** 任务执行器. */
	public static Executor mExecutorService = null;
	
	/** 保存线程数量 . */
	private static final int CORE_POOL_SIZE = 5;
	
	/** 最大线程数量 . */
    private static final int MAXIMUM_POOL_SIZE = 64;
    
    /** 活动线程数量 . */
    private static final int KEEP_ALIVE = 5;

    /** 线程工厂 . */
    private static final ThreadFactory mThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "AbThread #" + mCount.getAndIncrement());
        }
    };

    /** 队列. */
    private static final BlockingQueue<Runnable> mPoolWorkQueue =
            new LinkedBlockingQueue<Runnable>(10);
    
    /**
     * 获取执行器.
     *
     * @return the executor service
     */
    public static Executor getExecutorService() { 
        if (mExecutorService == null) { 
        	int numCores = getNumCores();
        	mExecutorService
	         = new ThreadPoolExecutor(numCores * CORE_POOL_SIZE,numCores * MAXIMUM_POOL_SIZE,numCores * KEEP_ALIVE,
                    TimeUnit.SECONDS, mPoolWorkQueue, mThreadFactory);
        }
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        return mExecutorService;
    }

    /**
     * Gets the number of cores available in this device, across all processors.
     * Requires: Ability to peruse the filesystem at "/sys/devices/system/cpu"
     * @return The number of cores, or 1 if failed to get result
     */
    public static int getNumCores() {
        try {
            //Get directory containing CPU info
            File dir = new File("/sys/devices/system/cpu/");
            //Filter to only list the devices we care about
            File[] files = dir.listFiles(new FileFilter(){

                @Override
                public boolean accept(File pathname) {
                    //Check if filename is "cpu", followed by a single digit number
                    if(Pattern.matches("cpu[0-9]", pathname.getName())) {
                        return true;
                    }
                    return false;
                }

            });
            //Return the number of cores (virtual CPU devices)
            return files.length;
        } catch(Exception e) {
            e.printStackTrace();
            return 1;
        }
    }


}
