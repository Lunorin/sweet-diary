declare module '*.vue' {
  import Vue from 'vue';
  export default Vue;
}

declare module 'is-promise'

declare module '@/config.json'

// 完全重写 vue-router 类型
declare module 'vue-router' {
  import Vue from 'vue'
  import { PluginFunction } from 'vue'

  export type RouterMode = 'hash' | 'history' | 'abstract'
  export type RawLocation = string | Location

  export interface Location {
    name?: string
    path?: string
    hash?: string
    query?: any
    params?: any
    append?: boolean
    replace?: boolean
  }

  export interface Route {
    path: string
    name?: string
    hash: string
    query: any
    params: any
    fullPath: string
    matched: RouteRecord[]
    redirectedFrom?: string
    meta?: any
    beforeEnter?: (to: Route, from: Route, next: Function) => void
  }

  export interface RouteRecord {
    path: string
    regex: RegExp
    components: any
    instances: any
    name?: string
    parent?: RouteRecord
    redirect?: string | ((to: Route) => string) | Location
    matchAs?: string
    meta: any
    beforeEnter?: (to: Route, from: Route, next: Function) => void
    props: any
  }

  export type RouteConfig = {
    path: string
    name?: string
    component?: any
    components?: any
    redirect?: string | ((to: Route) => string) | Location
    alias?: string | string[]
    children?: RouteConfig[]
    meta?: any
    beforeEnter?: (to: Route, from: Route, next: Function) => void
    caseSensitive?: boolean
    pathToRegexpOptions?: any
  }

  export interface RouterOptions {
    routes?: RouteConfig[]
    mode?: RouterMode
    base?: string
    linkActiveClass?: string
    linkExactActiveClass?: string
    scrollBehavior?: (to: Route, from: Route, savedPosition?: any) => any
    fallback?: boolean
    parseQuery?: (query: string) => any
    stringifyQuery?: (query: any) => string
  }

  export class Router {
    constructor(options?: RouterOptions)
    app: any
    mode: RouterMode
    currentRoute: Route
    options: RouterOptions
    push(location: RawLocation, onComplete?: Function, onAbort?: Function): Promise<Route>
    replace(location: RawLocation, onComplete?: Function, onAbort?: Function): Promise<Route>
    go(n: number): void
    back(): void
    forward(): void
    getMatchedComponents(to?: RawLocation | Route): any[]
    onReady(cb: Function, errorCb?: Function): void
    onError(cb: Function): void
    addRoutes(routes: RouteConfig[]): void
    resolve(to: RawLocation, current?: Route, append?: boolean): {
      location: Location
      route: Route
      href: string
      normalizedTo: Location
      resolved: Route
    }
    beforeEach(guard: NavigationGuard): Function
    afterEach(hook: (to: Route, from: Route) => void): Function
  }

  export type NavigationGuard = (
    to: Route,
    from: Route,
    next: (to?: RawLocation | false | Function | void) => void
  ) => any

  export const install: PluginFunction<never>
  const VueRouter: Router
  export default Router
}
