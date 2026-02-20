package org.setu.placemark.console.models

class PlacemarkMemStore : PlacemarkStore {
    var lastId = 0L

    val placemarks = ArrayList<PlacemarkModel>()

    override fun findAll(): List<PlacemarkModel> {
        return placemarks
    }

    override fun findOne(id: Long): PlacemarkModel? {
        var foundPlacemark: PlacemarkModel? = placemarks.find { p -> p.id == id }
        return foundPlacemark
    }

    fun getId(): Long {
        return lastId++
    }

    override fun create(placemark: PlacemarkModel) {
        placemark.id = getId()
        placemarks.add(placemark)
        logAll()
    }

    override fun update(placemark: PlacemarkModel) {
        val foundPlacemark = findOne(placemark.id)
        if (foundPlacemark != null) {
            println("The information you have entered is valid and will be updated.")
            foundPlacemark.title = placemark.title
            foundPlacemark.description = placemark.description
        }
    }

    override fun delete(placemark: PlacemarkModel) {
        placemarks.remove(placemark)
    }

    fun logAll() {
        placemarks.forEach { println("$it") }
    }
}
