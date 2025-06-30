# capacitor-jpush-plugin

A Capacitor plugin for JPush (极光推送) with integrated vendor channels support for Android and iOS

## Install

```bash
npm install capacitor-jpush-plugin
npx cap sync
```

## API

<docgen-index>

* [`init()`](#init)
* [`getRegistrationID()`](#getregistrationid)
* [`setAlias(...)`](#setalias)
* [`deleteAlias()`](#deletealias)
* [`addTags(...)`](#addtags)
* [`deleteTags(...)`](#deletetags)
* [`cleanTags()`](#cleantags)
* [`setBadge(...)`](#setbadge)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### init()

```typescript
init() => Promise<{ success: boolean; message: string; }>
```

Initialize JPush SDK

**Returns:** <code>Promise&lt;{ success: boolean; message: string; }&gt;</code>

--------------------


### getRegistrationID()

```typescript
getRegistrationID() => Promise<{ registrationId: string; }>
```

Get JPush registration ID

**Returns:** <code>Promise&lt;{ registrationId: string; }&gt;</code>

--------------------


### setAlias(...)

```typescript
setAlias(options: { alias: string; }) => Promise<{ success: boolean; message: string; }>
```

Set alias for the device

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ alias: string; }</code> |

**Returns:** <code>Promise&lt;{ success: boolean; message: string; }&gt;</code>

--------------------


### deleteAlias()

```typescript
deleteAlias() => Promise<{ success: boolean; message: string; }>
```

Delete alias for the device

**Returns:** <code>Promise&lt;{ success: boolean; message: string; }&gt;</code>

--------------------


### addTags(...)

```typescript
addTags(options: { tags: string[]; }) => Promise<{ success: boolean; message: string; }>
```

Add tags to the device

| Param         | Type                             |
| ------------- | -------------------------------- |
| **`options`** | <code>{ tags: string[]; }</code> |

**Returns:** <code>Promise&lt;{ success: boolean; message: string; }&gt;</code>

--------------------


### deleteTags(...)

```typescript
deleteTags(options: { tags: string[]; }) => Promise<{ success: boolean; message: string; }>
```

Delete specific tags from the device

| Param         | Type                             |
| ------------- | -------------------------------- |
| **`options`** | <code>{ tags: string[]; }</code> |

**Returns:** <code>Promise&lt;{ success: boolean; message: string; }&gt;</code>

--------------------


### cleanTags()

```typescript
cleanTags() => Promise<{ success: boolean; message: string; }>
```

Clean all tags from the device

**Returns:** <code>Promise&lt;{ success: boolean; message: string; }&gt;</code>

--------------------


### setBadge(...)

```typescript
setBadge(options: { badge: number; }) => Promise<{ success: boolean; message: string; }>
```

Set badge number (mainly for iOS)

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ badge: number; }</code> |

**Returns:** <code>Promise&lt;{ success: boolean; message: string; }&gt;</code>

--------------------

</docgen-api>
