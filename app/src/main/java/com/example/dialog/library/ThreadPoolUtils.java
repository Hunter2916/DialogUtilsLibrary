/*
* ECARX Technology Limited is the owner of the copyright and the trade secret of this software.
* Without permission, no one has the right to obtain, disclose or use this software in any way.
*/

package com.example.dialog.library;


import com.example.dialog.library.listener.Future;

public class ThreadPoolUtils {
    public static Future execute(Runnable r) {
        return ThreadPool.getInstance().submit((ThreadPool.Job<Void>) jc -> {
            r.run();
            return null;
        });
    }
}
