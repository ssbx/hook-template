package io.sysmo.hooks.template;

/**
 * Created by seb on 06/08/16.
 * Main hook application.
 */

class MyHook {
    private static MyHook instance = null;

    static MyHook getInstance()
    {
        if (instance == null) {
            instance = new MyHook();
        }
        return instance;
    }

    /**
     * This is where the entire application is initialized.
     */
    private MyHook() {
        // init and load database informations for example,
    }
}
