package com.alancamargo.lystchallenge.core.arch.di

import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.dsl.module

abstract class FeatureModule {

    open val domain = module { }
    open val data = module { }
    open val ui = module { }

    fun load() {
        loadKoinModules(modules = domain + data + ui)
    }

}
